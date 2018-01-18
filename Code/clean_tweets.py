import json
import re
import unicodedata

class Tweet(object):
	"""docstring for Tweet"""
	def __init__(self, id, content, ts):
		self.id = id
		self.content = content
		self.ts = ts
	
	def __eq__(self, other):
		return self.content == other.content

	def __ne__(self, other):
		return not self.__eq__(other)

	def __hash__(self):
		return hash(tuple(self.content))

	def __str__(self):
		return "[" + str(self.id) + ", " + str(self.content) + ", " + str(self.ts) + "]"

	def __repr__(self):
		return "[" + str(self.id) + ", " + str(self.content) + ", " + str(self.ts) + "]"

def read(filename):
	with open(filename) as fp:
		content = fp.readlines()
	content = [x.strip() for x in content]
	content = [item.split("\t") for item in content]
	content = [[item[6], item[8], idx] for idx, item in enumerate(content) if len(item) >= 7]
	content = [[item[0], re.sub(r"http\S+", "", item[1]), item[2]] for item in content]
	content = [[item[0], unicodedata.normalize('NFD', item[1]).encode('ascii', 'ignore'), item[2]] for item in content]
	content = [[item[0], item[1].decode('utf-8'), item[2]] for item in content]
	content = [[item[0], re.sub('[^A-Za-z0-9]+', ' ', item[1]), item[2]] for item in content]
	content = [[item[0], str.lower(item[1]), item[2]] for item in content]
	content = [[item[0], ''.join([i for i in item[1] if not i.isdigit()]), item[2]] for item in content]
	content = [[item[0], re.sub(r'\b\w{1,2}\b', '', item[1]), item[2]] for item in content]
	content = [[item[0], " ".join(item[1].split()), item[2]] for item in content]
	return content

def remove_duplicates(raw_list):
	tweet_list = [Tweet(item[0], item[1], item[2]) for item in raw_list]
	tweet_list = list(set(tweet_list))
	return tweet_list

def pack_in_dict(tweet_list):
	dict_list = [{"id":item.id, "content":item.content, "timestamp":idx} for idx, item in enumerate(tweet_list)]
	return dict_list

def conv_ts(timestamp):
	# hour = int(timestamp[0:2])
	# minutes = int(timestamp[3:5])
	# if timestamp[-2] == "A":
	# 	half = 1
	# else:
	# 	half = 2

	# ret = 0
	# if half == 1:
	# 	ret = 12*(hour - 6)
	# else:
	# 	if hour == 12:
	# 		ret = 72
	# 	else:
	# 		ret = 72 + 12*hour

	# if minutes <= 10:
	# 	ret += 0
	# elif minutes > 10 and minutes <= 20:
	# 	ret += 2
	# elif minutes > 20 and minutes <= 30:
	# 	ret += 4
	# elif minutes > 30 and minutes <= 40:
	# 	ret += 6
	# elif minutes > 40 and minutes <= 50:
	# 	ret += 8
	# else:
	# 	ret += 10

	# if minutes % 10 >= 5:
	# 	ret += 1
	# ret -= 7
	# print(hour, minutes, half)
	return int(timestamp / 50)

def write_in_files(dict_list):
	for item in dict_list:
		ts = conv_ts(item["timestamp"])
		filename = "./final_cleaned_tweets/" + str(ts) + ".json"
		with open(filename, "a") as outfile:
			json.dump(item, outfile)
			outfile.write("\n")

def main():
	# filename = "earthQuake_13Nov.txt"
	filename = "FACup/5_5_2012_16_16.txt"
	content = read(filename)
	content = remove_duplicates(content)
	content = sorted(content, key = lambda i: i.ts)
	content = pack_in_dict(content)
	# print(content)
	# print(len(content))
	# print(conv_ts(content[-1]["timestamp"]))
	write_in_files(content)

if __name__ == '__main__':
	main()