import random
import string

class RandomStringGenerator:
    def getRandomString(length):
        letters = string.ascii_letters
        result_str = ''.join(random.choice(letters) for i in range(length))
        return result_str
    