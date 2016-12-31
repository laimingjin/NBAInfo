import numpy as np
import scipy as sp
import matplotlib.pyplot as plt
from settings import STAR,TEAM,SEASONS,TO2
def get_name():
 #   f=open('c:\pp.txt')
 #   s=f.read()
    s = (open(SEASONS, "r").read()).splitlines()
    return s
def get_numbers():
    txtpath=STAR
    fp=open(STAR)
    sum=0
    A=[]
    for linea in fp.readlines():
        A.append(sum)
        sum=sum+1
 
    fp.close()
    return A
def draw(IMG_PATH):
    data=np.loadtxt(STAR)
    data2=np.loadtxt(TEAM)
  #  ax = plt.subplots()
   # ax.set_xticklabels( get_name())
    plt.plot(data,color='b')
    plt.xticks(get_numbers(),get_name())
    plt.plot(data2,color='r')

    plt.savefig(IMG_PATH)
#the code should not be changed
if __name__ == '__main__':
 

        #the path of image to show
        IMG_PATH = TO2
        draw(IMG_PATH)