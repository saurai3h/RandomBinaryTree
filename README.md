This program generates a random binary tree.

The input argument takes an integer (MAX number of nodes that the tree can have)

The output is in this format
<total nodes>
<node label> <node value>
<node label> <node value>
.....
.....
.....
.....
<node label> <node value>

node labels are 1 based. left child has label = (2 * labelParent) and right child has label = (2 * labelParent + 1)
It always prints a valid binary tree where labels need not be sorted.