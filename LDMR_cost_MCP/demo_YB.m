
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

% load training data
load('./data_YB/YB_subset1.mat')
trdat = [];
trls = train_label;
for i=1:size(train_data, 2)
    t = reshape(train_data(:,i), [192 168]);
     for j = 1:100
        for k= 1:160
            if p(j,k)~=0
                t(90+j,k+4)=p(j,k);
            end
        end
    end
    t = imresize(t, imgsize);
    %t(32:45, 7:35) = 1;
    t = t(:);
    trdat = [trdat t];
end
clear train_data train_label

load('data_YB/YB_subset4.mat')
ttdat = [];
ttls = train_label;
for i=1:size(train_data, 2)
    t = reshape(train_data(:,i), [192 168]);
    for j = 1:100
        for k= 1:160
            if p(j,k)~=0
                t(90+j,k+4)=p(j,k);
            end
        end
    end
    t = imresize(t, imgsize);
    %t(32:45, 7:35) = 1;
    t = t(:);
    ttdat = [ttdat t];
end
clear train_data train_label

n = size(ttdat, 2);
Proj = pinv(trdat'*trdat+0.01*size(trdat,2))*trdat';

alpha = 0.5;
beta  = 1e-3;
delta = 2;
lambda = 0.1;


boundary = round(1/10 * classnum);
%boundary = round(1/2 * classnum);

%for gamma = 11:1:20
gamma=17;
total_cost = 0;
ID=zeros(1,n);
errfp=0;
errfn=0;
tic
for i = 1:n
    %i=100;
    y = ttdat(:,i);
    Xs = Proj*y;
    Es = y-trdat*Xs;
    [w] = Weight(y, trdat, Xs, trls, delta);
    [X] = LDMR_MCP_cost(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize, gamma, i, ttls, boundary);
    %[X] = LDMR(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize);
    [label] = classifier(trdat, X, trls, imgsize);  
    ID(i)=label;
    if mod(i,10)==0
        fprintf('%d / %d \n',i,n);  
        %fprintf('%.2f\n',total_cost / n);
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
fprintf('cost: %.2f\n',total_cost / n);
fprintf('errfp: %.2f\n',errfp );
fprintf('errfn: %.2f\n',errfn );
fprintf('errfp: %.4f\n',errfp / n);
fprintf('errfn: %.4f\n',errfn / n);
toc
%end








