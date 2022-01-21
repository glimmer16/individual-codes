clear;clc
%
classnum = 38;
imgsize = [48 42];
%
p = imread('./pic.png');
p = rgb2gray(p);
imgsize = [100, 160];
p = imresize(p, imgsize);
p = im2double(p);
%imshow(p);
% load training data
load('./data_YB/YB_subset1.mat')
trdat = [];
trls = train_label;
for i=1:size(train_data, 2)
    t = reshape(train_data(:,i), [192 168]);
    %t(128:180, 28:140) = 1;
    for j = 1:100
        for k= 1:160
            if p(j,k)~=0
                t(90+j,k+4)=p(j,k);
            end
        end
    end
    %t(90:190, 4:164) = p;
    if mod(i,7)==0
        imshow(t);
        pause(1);
        format = strcat(num2str(i),'.png');
        path = './result2/';
        imwrite(t, strcat(path, format));
    end
    t = imresize(t, imgsize);
    t = t(:);
    trdat = [trdat t];
end
clear train_data train_label
% 
% load('data_YB/YB_subset4.mat')
% ttdat = [];
% ttls = train_label;
% for i=1:size(train_data, 2)
%     t = reshape(train_data(:,i), [192 168]);
%     if mod(i,7)==0
%         imshow(t);
%         pause(1);
%     end
%     t = imresize(t, imgsize);
%     t = t(:);
%     ttdat = [ttdat t];
% end
% clear train_data train_label