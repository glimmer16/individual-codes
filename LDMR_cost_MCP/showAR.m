clear;clc
%
classnum = 50;
imgsize = [55 40];
%
% load training data
load('./data_AR/AR.mat')
trdat = [];
trls = [];
%trls = gnd(1:520);
for i=1:size(data(:,1:1300), 2)
    if mod(i,26)>0&&mod(i,26)<14
        t = reshape(data(:,i), [55 40]);
        t = imresize(t, imgsize);
        % imagesc(t)
        % colormap(flipud(gray))
        t = t(:);
        t=normalize(t,'range');
        trdat = [trdat t];
        trls = [trls gnd(:,i)];
    end
end

clear data gnd

load('data_AR/AR.mat')
ttdat = [];
ttls = [];
%ttls = gnd(1:520);
for i=1:size(data(:,1:1300), 2)
    if mod(i,26)==0||mod(i,26)>13
        t = reshape(data(:,i), [55 40]);        
        if mod(i,26)==0
           imshow(normalize(t,'range'));
           pause(1);
        end
        t = imresize(t, imgsize);
        t = t(:);
        t=normalize(t,'range');
        ttdat = [ttdat t];
        ttls = [ttls gnd(:,i)];
    end
end

clear data gnd