from mlp import MLP
import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from datetime import datetime
from concurrent.futures import ThreadPoolExecutor

np.random.seed(42)

matplotlib.use('Agg')

x = np.linspace(0, 2 * np.pi, 100)
t = np.sin(x) + np.random.normal(0, 0.1, x.shape)

x = x.reshape((-1, 1))
t = t.reshape((-1, 1))

hidden_layers_sizes = [
    [100],
    [200],
    [100, 100],
    [100, 200],
    [200, 100],
    [200, 200]
]

learning_rate = 0.1

def test_mlp(i):

    mlp = MLP([1, *hidden_layers_sizes[i], 1], activation="tanh")

    error, epoch = mlp.train(x, t, learning_rate)

    y = mlp.predict(x)

    print(f"Test #{i+1} finished {datetime.now()}")

    return y, error, epoch

with ThreadPoolExecutor(max_workers=3) as executor:
    results = executor.map(test_mlp, range(len(hidden_layers_sizes)))

    for i, result in enumerate(results):
        y, error, epoch = result
        plt.plot(x, t, color='blue', label='Target')
        plt.plot(x, y, color='green', label='Predicted')

        plt.title(f"layers={hidden_layers_sizes[i]} | e={error:.4f} | α={learning_rate} ")
        plt.xlabel('x')
        plt.ylabel('y')
        plt.legend()

        plt.savefig(f"test_sin/test{i + 1}.png", format='png')

        plt.close()