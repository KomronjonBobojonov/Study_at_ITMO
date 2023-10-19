import re

def count_smileys(text):
    pattern = r'[<P'
    smiley_list = re.findall(pattern, text)
    return len(smiley_list)