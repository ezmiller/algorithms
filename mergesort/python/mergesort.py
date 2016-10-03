# Merge sort
#
# How it works:
#   1. Divide the unsorted list into n sublists, each containing 1 element
#   2. Repeatedly merge sublists to produce new sorted sublists until there
#      is only 1 sublist remaining. This will be the sorted list.

def merge(l, r):
    # print('merge():: l => {} r => {}'.format(l,r))
    l_idx = 0
    r_idx = 0
    merged = []
    while l_idx < len(l) and r_idx < len(r):
        if l[l_idx] > r[r_idx]:
            merged.append(r[r_idx])
            r_idx += 1
        else:
            merged.append(l[l_idx])
            l_idx += 1
    while l_idx < len(l):
        merged.append(l[l_idx])
        l_idx += 1
    while r_idx < len(r):
        merged.append(r[r_idx])
        r_idx += 1
    return merged

def merge_sort(list):
    # print('merge_sort()::list {}'.format(list))
    if len(list) == 1:
        return list
    mid = int(len(list) / 2)
    left = list[:mid]
    right = list[mid:]
    return merge(merge_sort(left), merge_sort(right))
