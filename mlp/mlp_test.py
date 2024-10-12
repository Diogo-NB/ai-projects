import numpy as np
from mlp import MLP
import matplotlib.pyplot as plt

x = np.linspace(0, 1, 10)
t = np.sin(np.pi * x)

x = x.reshape(-1, 1)
t = t.reshape(-1, 1)

input_size = x.shape[1]
output_size = t.shape[1]

hidden_layers = [3]

mlp = MLP(layers_sizes=[input_size, *hidden_layers, output_size], learning_rate=0.01)   

error, epochs = mlp.train(x, t)
print(f"Error: {error}, epochs: {epochs}")

y = mlp.predict(x)

plt.plot(x, t)
plt.plot(x, y)
plt.show()