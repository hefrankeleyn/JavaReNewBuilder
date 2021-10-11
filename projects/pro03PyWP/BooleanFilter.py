import mmh3
from bitarray import bitarray

class BooleanFilter:
    def __init__(self, hash_num, size):
        self.size = size
        self.hash_num = hash_num
        self.bit_array = bitarray(size)
        self.bit_array.setall(0)

    def add(self, s):
        for seed in range(self.hash_num):
            res = mmh3.hash(s, seed) % self.size
            self.bit_array[res] = 1

    
    def search(self, s):
        for seed in range(self.hash_num):
            res = mmh3.hash(s, seed) % self.size
            if self.bit_array[res]==0:
                return 'None'
        return 'Probably'


if __name__=='__main__':
    bf = BooleanFilter(7, 50000)
    bf.add('word')
    res=bf.search('word')
    print(res)
    print(bf.search('hello'))