import numpy as np
import matplotlib.pyplot as plt
from settings import WIN,LOSE,WINPER,TIME,TO3
def get_numbers():
    txtpath='c:\playersTeamWin.txt'
    fp=open(txtpath)
    sum=0
    for linea in fp.readlines():
        sum=sum+1
 
    fp.close()
    return sum
def get_data():
  data=np.loadtxt(WIN)
  return data
def get_data2():
  data=np.loadtxt(LOSE)
  return data
def get_name():
 #   f=open('c:\pp.txt')
 #   s=f.read()
    s = (open(TIME, "r").read()).splitlines()
    return s
N = get_numbers()
menMeans = get_data()

 
ind = np.arange(N)  # the x locations for the groups
width = 0.35       # the width of the bars
 
fig, ax = plt.subplots()
rects1 = ax.bar(ind, menMeans, width, color='r')
 
womenMeans = get_data2()

rects2 = ax.bar(ind+width, womenMeans, width, color='y')
 
# add some
ax.set_ylabel('PER')
ax.set_title('PER by players and time')
ax.set_xticks(ind+width)
ax.set_xticklabels( get_name())
 
ax.legend( (rects1[0], rects2[0]), ('Before', 'After') )
 
def autolabel(rects):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x()+rect.get_width()/2., 1.05*height, '%d'%int(height),
                ha='center', va='bottom')
def draw(IMG_PATH): 
    autolabel(rects1)
    autolabel(rects2)
    data=np.loadtxt(WINPER)
    plt.plot(data)
    plt.savefig(IMG_PATH)
if __name__ == '__main__':
 

        #the path of image to show
        IMG_PATH = TO3
        draw(IMG_PATH)
