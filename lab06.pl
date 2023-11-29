% Predicate Logic Rules
is_parent(john, sarah).
is_parent(john, mike).
is_parent(mary, sarah).
is_parent(mary, mike).

is_grandparent(X, Z) :- is_parent(X, Y), is_parent(Y, Z).
