import random as rd
import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

class Tree:

    def get_triangle(self, b, h, y_offset):
        return [-b/2, 0, b/2], [y_offset, y_offset + h, y_offset]

    def get_balls(self, b, h, y_offset, n):
        line = lambda x: 2*h*x/b
        balls = []
        while len(balls) < n:
            x = rd.random()*b/2
            y = rd.random()*h
            if line(x) >= y:
                balls.append((x - b/2, y + y_offset))
            else:
                balls.append((x, h-y + y_offset))
        return balls
    
    
    
    def draw(self):
        plt.figure(figsize=(8, 8))
        plt.scatter([0], [19/3], marker='*', s=2000, zorder=4, c='yellow', edgecolors='gray', label='Stern')
        plt.fill([-0.5, 0.5, 0.5, -0.5], [0, 0, 1, 1], c='brown', label='Stamm')
        plt.fill(*self.get_triangle(6, 4, 1), c='green', label='Nadeln')
        plt.fill(*self.get_triangle(5, 10/3, 2), c='green')
        plt.fill(*self.get_triangle(4, 8/3, 3), c='green')
        plt.fill(*self.get_triangle(3, 6/3, 4), c='green')
        plt.fill(*self.get_triangle(2, 4/3, 5), c='green')

        red_balls = self.get_balls(5.2, 5, 1.25, 25)
        yellow_balls = self.get_balls(5.2, 5, 1.25, 25)
        plt.scatter(*zip(*red_balls), c='red', zorder=2, s=100, label='rote Kugeln')
        plt.scatter(*zip(*yellow_balls), c='yellow', zorder=3, s=100, label='gelbe Kugeln')
        
        xrange = np.linspace(-3, 3, 300)
        yrange = 1 + 11.25*stats.norm.pdf(xrange, loc=0, scale=.9)
        plt.plot(xrange, yrange, color='cornflowerblue', linestyle='--', label='Lametta')


        plt.title('Weihnachtsbaum')
        plt.xlabel('Breite')
        plt.ylabel('Höhe')
        lgnd = plt.legend()
        lgnd.legendHandles[3]._sizes = [200]
        plt.grid()
        plt.axis('equal')
        plt.show()