import unittest
import random
from mergesort import *

class TestMergeMethod(unittest.TestCase):

    def test_basic(self):
        a = [3, 10]
        b = [2, 7]
        assert merge(a,b) == [2,3,7,10]

    def test_on_different_size_lists(self):
        a = [10, 20, 30, 40, 50]
        b = [1, 22, 28]
        assert merge(a,b) == [1, 10, 20, 22, 28, 30, 40, 50]

class TestMergeSortMethod(unittest.TestCase):

    def test_basic(self):
        list = [1, 3, 2, 4]
        assert merge_sort(list) == [1, 2, 3, 4]

    def test_gigantic_list(self):
        list = random.sample(range(1,1000), 999)
        assert merge_sort(list) == sorted(list)


if __name__ == '__main__':
    unittest.main()

