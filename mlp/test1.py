import numpy as np
from mlp import MLP

w1 = np.array([
    [0.12, -0.03],
    [-0.04, 0.15],
    [0.31, -0.41],
])
w2 = np.array([
    [-0.05, -0.34, 0.21],
    [0.19, -0.09, 0.26]
])
test_w = [w1, w2]

b1 = np.array([-0.09, 0.18])
b2 = np.array([0.18, -0.27, -0.12])
test_b = [b1, b2]

layers_sizes = [3, 2, 3]

mlp = MLP(layers_sizes=layers_sizes, initial_state=[(w1, b1), (w2, b2)])   

inputs_data = [1, 0.5, -1]
outputs_data = [1, -1, -1]

total_error, epochs = mlp.train(inputs=np.array([inputs_data]), targets=np.array([outputs_data]))

print(f"{total_error=}, {epochs=}")
for i in range(len(layers_sizes) - 1):
    print(f"\nentre as camadas nº{i+1} e nº{i+2}")
    print(f"w:\n{mlp.layers_connections[i].w}")
    print(f"b:\n{mlp.layers_connections[i].b}")