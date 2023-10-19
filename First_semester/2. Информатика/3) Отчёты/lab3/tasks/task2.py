import re

def solve(string):
	pattern = r'\b\w*[аеёиоуыэюя][аеёиоуыэюя]\w*\b'
	matches = re.findall(pattern, string)
	pattern2 = r'\b\w*[бвгджзйклмнпрстфхцчшщ]{1,3}\w*\b'
	filtered_matches = [word for word in matches if not re.search(pattern2, word)]
	
	return filtered_matches