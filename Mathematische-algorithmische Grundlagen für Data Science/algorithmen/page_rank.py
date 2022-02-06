import pandas as pd
import numpy as np
from daten import names, links

def make_a(names,links):
    n = len(names)
    A = np.zeros((n,n))
    i = 0.0
    for j in range(0,n,1):
        for k in range(0,len(links[j]),1):
            i = links[j][k]
            A[i][j] = 1.0 / float(len(links[j]))
    return A


def make_p():
    n = len(names)
    p = np.zeros(n)
    for i in range(0,n,1):
        p[i] = 1.0 / float(n)
    return p

def dump(p,q,d):
    n = len(names)
    pneu = np.zeros(n)
    psum = 0.0
    for i in range(0,n,1):
        psum = psum + p[i]
    for i in range(0,n,1):
        pneu[i] = (1-d)*psum / n + d*q[i]

    return pneu

max_iter = 50
d = .95
A = make_a(names=names,links=links)
p = make_p()

for iter in range(1,max_iter+1,1):
    q = A@p
    p = dump(p=p,q=q,d=d)

ausgabe = dict(zip(names, p))


