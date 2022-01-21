function [label, nm] = classifier(A, X, trls, imgsize)

classnum = numel(unique(trls));

nm = zeros(1,classnum);
for i=1:classnum
    pos = find(trls == i);
    Xi  = X(pos);
    Ai = A(:,pos);
    r = reshape(A*X - Ai*Xi, imgsize);
    %fprintf('%.2f\n',norm(Ai*Xi));
    nm(i) = sum(svd(r));
end

index = find(nm==min(nm));
label = index(1);
end

