import numpy as np
import math
from itertools import permutations,combinations

A = np.array([[1,1,1,0,0],
              [5,2,0,1,0],
              [0,1,0,0,1]])
y = np.array([10,30,9])
c = np.array([30,25,0,0,0])

z = -100000
pos_z = None

m,n= A.shape
spaltenmengen = math.comb(n,m)
xerg = np.zeros(n)
perm = combinations([i for i in range(1,n+1,1)],m)

print(f'all {spaltenmengen} combinations: ')
for i in list(perm):
    x = np.zeros(n)
    neg=False
    p1,p2,p3 = i # So viele p0-pm !! Muss evtl. hinzugefügt werden
    pm1, pm2, pm3 =p1-1,p2-1,p3-1 # Anpassung auf Matrix form, beginnend bei 0
    AB = np.zeros((m, m))
    for i,p in enumerate([pm1, pm2, pm3]):
        AB[i] = A[:,p]
    AB = AB.transpose()
    if np.linalg.det(AB) == 0:
        print(f'({p1},{p2},{p3})', '->', 'Nicht Regulär det(C) = 0')
        continue
    try:
        ecke = np.linalg.solve(AB,y)
    except  np.linalg.LinAlgError:
        print(f'({p1},{p2},{p3})','->','Linear Abhängig')
    for el in ecke:
        if el <0:
            print(f'({p1},{p2},{p3})', '->', 'Liefert negativen Wert für ein x.')
            neg = True
            break

    if neg: continue
    x[pm1] = ecke[0]
    x[pm2] = ecke[1]
    x[pm3] = ecke[2]
    
    tmp = c.transpose()@x
    if tmp > z:
        z = tmp
        xerg = x
        pos_z = p1,p2,p3
    print(f'({p1},{p2},{p3})','->',x)

print('Ergebnis:')
print('z = ',z)
print(pos_z, '->',xerg )


