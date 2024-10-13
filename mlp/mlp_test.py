# from mlp import MLP as LinearGradMLP
from mlp_adagrad import MLP as AdaGradMLP
import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from datetime import datetime
from concurrent.futures import ThreadPoolExecutor

np.random.seed(122)

matplotlib.use('Agg')

def normalize_array(arr):
    norm_arr = (arr - np.min(arr)) / (np.max(arr) - np.min(arr))
    return norm_arr

test_name = "exp_sin"

test_results_dir = "tests"

x = np.linspace(0, 2 * np.pi, 100)
t = np.exp(-x) * np.sin(10 * x)
t = normalize_array(t)

x = x.reshape((-1, 1))
t = t.reshape((-1, 1))

hidden_layers_sizes = [
    [200, 150, 100, 50, 33, 15, 5]
]

learning_rate = 0.01
max_epochs : int = 15000

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

    print("All results saved - adagrad")    

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

#         print("All results saved - lineargrad")           