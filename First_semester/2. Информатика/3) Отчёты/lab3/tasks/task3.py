import re

def find_words_with_single_vowel(text):
    vowels = 'АЕЁИОУЫЭЮЯаеёиоуыэюя'
    words = re.findall(r'\b\w+\b', text)
    words_with_single_vowel = []

    for word in words:
        for vowel in vowels:
            without_vowels = re.sub(f'[{vowels}]', '', vowels)
            if re.search(rf"^[^{without_vowels}]*[{vowel}]+[^{without_vowels}]*$", word):
                words_with_single_vowel.append(word)

    return sorted(set(words_with_single_vowel), key=lambda x: (len(x), x))
