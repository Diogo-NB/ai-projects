import numpy as np
from mlp import MLP

x = [
    np.array([1, 1]),
    np.array([1, -1]),
    np.array([-1, 1]),
    np.array([-1, -1])
]

t0 = np.array([1, -1])
t1 = np.array([-1, -1])

t = [
    t0,
    t1,
    t1,
    t0
]

mlp = MLP(layers_sizes=[len(x[0]), 4, len(t[0])], tolerated_error=1e-3, learning_rate=0.01)

total_error, epochs = mlp.train(inputs=x, targets=t)

for i in range(len(x)):
    y = mlp.predict(x[i])
    print(f"{x[i]} -> {y} == {t[i]}")

print(total_error, epochs)
