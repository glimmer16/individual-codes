clear;clc
%
classnum = 40;
imgsize = [32 32];
%
% load training data
load('./data_ORL/ORL_32x32.mat')
trdat = [];
trls = [];
ttdat = [];
ttls = [];
gnd = gnd';
for i=0:classnum-1
    x = randperm(10);
    tr_x = x(1:8);
    for j = 1:8
        t = reshape(fea(tr_x(j) + 10*i, :), imgsize);
        % imagesc(t)
        % colormap(flipud(gray))
        t = t(:);
        t = normalize(t,'range');
        trdat = [trdat t];
    end
    trls = [trls gnd(tr_x + 10*i)];
    
    tt_x = x(9:10);
    for j = 1:2 
        t = reshape(fea(tt_x(j)+ 10*i, :), imgsize);
        t = t(:);
        t = normalize(t,'range');
        ttdat = [ttdat t];
    end
    ttls = [ttls gnd(tt_x + 10*i)];
end
 clear fea gnd

n = size(ttdat, 2);
Proj = pinv(trdat'*trdat+0.01*size(trdat,2))*trdat';

alpha = 0.5;
beta  = 1e-3;
delta = 2;
lambda = 0.1;


boundary = round(1/10 * classnum);

for gamma = 1:1:10
total_cost = 0;
ID=zeros(1,n);
tic
for i = 1:n
    y = ttdat(:,i);
    Xs = Proj*y;
    Es = y-trdat*Xs;
    [w] = Weight(y, trdat, Xs, trls, delta);
    %[X] = LDMR_MCP_cost(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize, gamma, i, ttls, boundary);
    [X] = LDMR(y, trdat, w, trls, Xs, Es, alpha, beta, imgsize);
    [label] = classifier(trdat, X, trls, imgsize);  
    ID(i)=label;
    if mod(i,40)==0
        fprintf('%d / %d \n',i,n);    
    end
    c1 = cost(X, trdat, i, trls,imgsize,ttls, boundary);
    total_cost = total_cost + c1;
end
acc = mean(ttls(:)==ID(:));
fprintf('Acc: %.2f\n',acc*100);
fprintf('gamma: %.2f\n',gamma);
fprintf('cost: %.2f\n',total_cost);
toc
end








