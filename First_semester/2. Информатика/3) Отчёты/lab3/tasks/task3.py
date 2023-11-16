#408281 % 6 = 1

import re

def solve(text):
	words = re.findall(r'\b\w+\b', text)
	words_with_one_vowel = []
	for word in words:
		for vowel in ['Аа', 'Ее', 'Ёё', 'Ии', 'Оо', 'Уу', 'Ыы', 'Ээ', 'Юю', 'Яя']:
			x = re.sub(f'[{vowel}]', '', 'АЕЁИОУЫЭЮЯаеёиоуыэюя')
			if re.search(rf"^[^{x}]*[{vowel}]+[^{x}]*$", word):
				words_with_one_vowel.append(word)

	return sorted(set(words_with_one_vowel), key=lambda x: (len(x), x))