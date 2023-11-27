#408281 % 6 = 5 [
#408281 % 4 = 1 <
#408281 % 7 = 6 P

import re

def solve(string):
    pattern = r"\[<P"
    smiley_list = re.findall(pattern, string)
    return len(smiley_list)