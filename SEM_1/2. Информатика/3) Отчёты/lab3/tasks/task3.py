#408281 % 6 = 1
import re

def solve(text):
    words = map(lambda x: re.sub(r"[^\w-]", "", x), text.split())

    words = list(filter(None, words))

    res = [w for w in words if len(set(re.findall("[аиуэыоеёюя]", w.lower()))) == 1]

    return sorted(res, key=lambda x: (len(x), x.lower()))