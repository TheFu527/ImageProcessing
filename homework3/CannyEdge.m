clear;
clc;
f= imread('lenna.jpg');
f=rgb2gray(f);
subplot(1,2,1),imshow(f);
[x,y]=size(f);
th=graythresh(f);
img_bi=im2bw(f,th);%???????
temp=fspecial('gaussian',3,0.8);
imageFilter=imfilter(img_bi,temp);
dx=[-1,-1,-1;0,0,0;1,1,1];
dy=[-1,0,1;-1,0,1;-1,0,1];%prewitt??????,????prewitt???????
imageFilter=double(imageFilter);
gradx=conv2(imageFilter,dx,'same');
grady=conv2(imageFilter,dy,'same');
grad=sqrt((gradx.^2)+(grady.^2));
dir=atan2(grady,gradx);
dir=dir*180/pi;
for i=1:x
    for j=1:y
        if((dir(i,j)>=-22.5&&dir(i,j)<=22.5)||(dir(i,j)>=157.5&&dir(i,j)<=180)||(dir(i,j)<=-157.5&&dir(i,j)>=-180))
            dir(i,j)=0;
        elseif((dir(i,j)>22.5&&dir(i,j)<=67.5)||(dir(i,j)<=-112.5&&dir(i,j)>-157.5)) 
            dir(i,j)=-45;
        elseif((dir(i,j)>67.5&&dir(i,j)<=112.5)||(dir(i,j)<=-67.5&&dir(i,j)>-112.5)) 
            dir(i,j)=90;
        elseif((dir(i,j)>112.5&&dir(i,j)<157.5)||(dir(i,j)<-22.5&&dir(i,j)>-67.5))
            dir(i,j)=45;
   end
end
end

for i=2:(x-1)
    for j=2:(y-1)
        if(dir(i,j)==90&&grad(i,j)==max([grad(i,j),grad(i,j+1),grad(i,j-1)]))
              Nomax(i,j)=grad(i,j);
        elseif(dir(i,j)==-45&&grad(i,j)==max([grad(i,j),grad(i+1,j-1),grad(i-1,j+1)]))
              Nomax(i,j)=grad(i,j);
        elseif(dir(i,j)==0&&grad(i,j)==max([grad(i,j),grad(i+1,j),grad(i-1,j)]))
              Nomax(i,j)=grad(i,j);
        elseif(dir(i,j)==45&&grad(i,j)==max([grad(i,j),grad(i+1,j+1),grad(i-1,j-1)]))
              Nomax(i,j)=grad(i,j);
        end
    end
end
%??????????
img=zeros(x,y);
thL=0.1*max(max(Nomax));
thH=0.3*max(max(Nomax));%?????3:1???????max??????????
for i=1:x-1
    for j=1:y-1
        if(Nomax(i,j)<thL)
            img(i,j)=0;
        elseif(Nomax(i,j)>thH)
            img(i,j)=1;
        elseif(max([Nomax(i-1,j),Nomax(i+1,j),Nomax(i,j+1),Nomax(i,j-1),Nomax(i-1,j-1),Nomax(i-1,j+1),Nomax(i+1,j-1),Nomax(i+1,j+1)])>thH)
            img(i,j)=1;
        end
    end
end

subplot(1,2,2),imshow(img);