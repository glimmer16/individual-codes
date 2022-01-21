clear;clc
p = imread('./pic.png');
p = rgb2gray(p);
imgsize = [100, 140];
p = imresize(p, imgsize);
imshow(p);