import numpy as np
from scipy.linalg import lu
import warnings
warnings.filterwarnings('ignore')

def backward(A: np.ndarray, b: np.ndarray) -> np.ndarray:
    n = b.size
    x = np.zeros_like(b)

    if A[n-1, n-1] == 0: raise ValueError

    x[n-1] = b[n-1]/A[n-1, n-1]
    C = np.zeros((n,n))

    for i in range(n-2, -1, -1):
        bb = 0
        for j in range (i+1, n):
            bb += A[i, j]*x[j]
        C[i, i] = b[i] - bb
        x[i] = C[i, i]/A[i, i]
    return x

def SolveR(R,b):
    n = len(b)
    x = np.zeros(n)
    x[-1] = b[-1] / R[-1,-1]
    for k in range(n-2,-1,-1):
        x[k] = (b[k] - np.sum(R[k,k+1]*x[k+1:])) /R[k,k]
    return x

def forward(L, b):
    x = []
    for i in range(len(b)):
        x.append(b[i])
        for j in range(i):
            x[i]=x[i]-(L[i, j]*x[j])
        x[i] = x[i]/L[i, i]
    return x


# Die LR zerlegung ist nicht eindeutig.
def lu_decomp(A):
    pl, u = lu(A,permute_l=True)
    print(pl)
    print(u)

def LR(A):
    m,n = A.shape
    L = np.eye(n)
    R = A.astype('float')
    for k in range(n-1):
        for j in range(k+1,m):
            L[j,k] = R[j,k]/R[k,k]
            R[j,k:] = R[j,k:]-L[j,k]*R[k,k:]
    return (L,R)



if __name__== '__main__':
    A  = np.matrix(([2,1,3,-5],[-2,-2,-2,-4],[4,-6,10,8],[6,12,8,14]))

    R = np.array([[2,1,3,5],
                  [0,-1,1,1],
                  [0,0,-4,-10],
                  [0,0,0,-12]])

    L = np.array([[1,0,0,0],
                  [-1,1,0,0],
                  [2,8,1,0],
                  [3,-9,-2,1]])

    b = np.array([-6,6,14,-34])

    # Ly = b
    y = forward(L=L, b=b)
    y = np.array(y)
    # Rx = y
    x = backward(A=R, b=y)

    print('Ergebnis y: ', y)
    print('Ergebnis x: ', x)

    L, R = LR(A)

    print('L:\n', L)
    print('R:\n', R)