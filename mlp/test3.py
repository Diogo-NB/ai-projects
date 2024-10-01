import numpy as np
from mlp import MLP

fn = lambda x1, x2: np.sin(x1) ** 2 + np.cos(x2) ** 2
fn = np.vectorize(fn)

n = 10
aux = np.linspace(-1.75, +1.75, n)

x = np.zeros((n*n, 2))
y = np.zeros((n*n, 1))

for i in range(n):
    for j in range(n):
        k = i*n + j
        x[k] = [aux[i], aux[j]]
        y[k] = fn(aux[i], aux[j])

mlp = MLP(layers_sizes=[2, 2, 2, 2, 1], learning_rate=0.1, tolerated_error=1e-4)

total_error, epochs = mlp.train(inputs=x, targets=y)
