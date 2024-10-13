# from mlp import MLP as LinearGradMLP
from mlp_adagrad import MLP as AdaGradMLP
import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from datetime import datetime
from concurrent.futures import ThreadPoolExecutor

np.random.seed(42)

matplotlib.use('Agg')

test_name = "seno_ruido"

test_results_dir = "tests"

x = np.linspace(-1, +1, 100)
t = np.sin(x) + np.random.normal(0, 0.1, x.shape)

x = x.reshape((-1, 1))
t = t.reshape((-1, 1))

hidden_layers_sizes = [
    [50, 50, 50, 50, 50, 50],
    [30, 30, 30, 30, 30, 30, 30, 30],
    [100, 90, 80, 70, 60, 50, 40, 30, 20, 10],
    [75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75]
]

learning_rate = 0.01
max_epochs : int = 25000

def test_ada_grad_mlp(i):

    mlp = AdaGradMLP([1, *hidden_layers_sizes[i], 1], activation="tanh")

    error, epoch = mlp.train(x, t, learning_rate=learning_rate, max_epochs=max_epochs,tolerated_error=1e-5)

    y = mlp.predict(x)

    print(f"Test #{i+1} finished - ada grad - {datetime.now()}")

    return y, error, epoch

# def test_linear_grad_mlp(i):

#     mlp = LinearGradMLP([1, *hidden_layers_sizes[i], 1], activation="tanh")

#     error, epoch = mlp.train(x, t, learning_rate=learning_rate, max_epochs=max_epochs)

#     y = mlp.predict(x)

#     print(f"Test #{i+1} finished - linear grad - {datetime.now()}")

#     return y, error, epoch

with ThreadPoolExecutor(max_workers=4) as executor:
    results = executor.map(test_ada_grad_mlp, range(len(hidden_layers_sizes)))

    for i, result in enumerate(results):
        y, error, epoch = result
        plt.plot(x, t, color='blue', label='Target')
        plt.plot(x, y, color='green', label='Predicted')

        plt.title(f"layers={hidden_layers_sizes[i]} | e={error:.4f} | α={learning_rate} ")
        plt.xlabel('x')
        plt.ylabel('y')
        plt.legend()

        plt.savefig(f"{test_results_dir}/adagrad/{test_name}_{i + 1}.png", format='png')

        plt.close()   

    print("All results saved")    

# with ThreadPoolExecutor(max_workers=4) as executor:
#     results = executor.map(test_linear_grad_mlp, range(len(hidden_layers_sizes)))

#     for i, result in enumerate(results):
#         y, error, epoch = result
#         plt.plot(x, t, color='blue', label='Target')
#         plt.plot(x, y, color='green', label='Predicted')

#         plt.title(f"layers={hidden_layers_sizes[i]} | e={error:.4f} | α={learning_rate} ")
#         plt.xlabel('x')
#         plt.ylabel('y')
#         plt.legend()

#         plt.savefig(f"{test_results_dir}/lineargrad/{test_name}_{i + 1}.png", format='png')

#         plt.close()            