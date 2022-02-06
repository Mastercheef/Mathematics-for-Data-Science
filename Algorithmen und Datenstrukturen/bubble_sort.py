def bubbleSort(arr):
    n = len(arr)
    for i in range(n - 1):
        print(arr)
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]


# Array eingeben: 
arr = [3, 14, 26, 1, 17, 9, 19, 4]
bubbleSort(arr)

