f=imread('lenna.jpg');
f=rgb2gray(f);
subplot(1,2,1),imshow(f);
[x,y]=size(f);
a=[-1,0,1;-2,0,2;-1,0,1];
b=[-1,-2,-1;0,0,0;1,2,1];%sobel??
gradx=imfilter(f,a,'same');
grady=imfilter(f,b,'same');
grad=gradx+grady;
subplot(1,2,2),imshow(grad)