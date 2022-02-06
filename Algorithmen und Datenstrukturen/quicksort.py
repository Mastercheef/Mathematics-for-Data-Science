def quick_sort(array):
    left = []
    equal = []
    right = []
    greater = []

    if len(array) > 1:
        pivot = array[0]
        for x in array:
            if x < pivot:
                left.append(x)
            elif x == pivot:
                equal.append(x)
            elif x > pivot:
                greater.append(x)
        return quick_sort(left) + equal + quick_sort(greater)  # recursive calling of the sort() function
    else:  # return the array, when it contains only 1 element
        return array

print(quick_sort([14,10,14,1,20,16,3]))