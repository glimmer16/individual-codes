
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
        t = imresize(t, imgsize);
        t = t(:);
        t=normalize(t,'range');
        ttdat = [ttdat t];
        ttls = [ttls gnd(:,i)];
    end
end

clear data gnd

n = size(ttdat, 2);
Proj = pinv(trdat'*trdat+0.01*size(trdat,2))*trdat';

alpha = 0.5;
beta  = 1e-3;
delta = 2;
lambda = 0.1;

boundary = round(0.1 * classnum);
%for gamma = 10:1:20
gamma=10;
total_cost = 0;
ID=zeros(1,n);
errfp=0;
errfn=0;
tic
for i = 1:n  
    y = ttdat(:,i);
    Xs = Proj*y;
    Es = y-trdat*Xs;
    [w] = Weight(y, trdat, Xs, trls, delta);
    [X] = LDMR_MCP_cost(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize, gamma, i, ttls, boundary);
    %[X] = LDMR(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize);
    [label] = classifier(trdat, X, trls, imgsize);  
    ID(i)= label;
    if mod(i,10)==0
        fprintf('%d / %d \n',i,n); 
        %fprintf('%.2f\n',total_cost/n);   
    end
    c1 = cost(X, trdat, i, trls,imgsize,ttls, boundary);
    if c1 ==20
       errfp=errfp+1; 
    elseif c1 ==2
       errfn=errfn+1; 
    end
    total_cost = total_cost + c1;
end


acc = mean(ttls(:)==ID(:));
fprintf('Acc: %.2f\n',acc*100);
fprintf('gamma: %.2f\n',gamma);
fprintf('cost: %.2f\n',total_cost/n); 
fprintf('errfp: %.2f\n',errfp );
fprintf('errfn: %.2f\n',errfn );
fprintf('errfp: %.4f\n',errfp / n);
fprintf('errfn: %.4f\n',errfn / n);
toc
%end






