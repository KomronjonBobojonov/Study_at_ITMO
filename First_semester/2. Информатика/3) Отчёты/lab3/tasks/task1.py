import re

def solve(string):
    """ Возвращает количество смайликов вида [<P в строке
    408281 % 6 = 5 => Глаза: [
    408281 % 4 = 1 => Нос: <
    408281 % 7 = 6 => Рот: P
    """
    pattern = r'[<P'
    return len(re.findall(pattern, string))
