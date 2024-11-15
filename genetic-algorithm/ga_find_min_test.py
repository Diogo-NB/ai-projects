from matplotlib import pyplot as plt
import numpy as np

from selection_methods import RouletteWheel, Tournament
from ga import GA

tests_results_dir = 'tests_results'

if (__name__ == '__main__'):
    g = lambda x: - np.abs(x * np.sin(np.sqrt(np.abs(x))))
    f = lambda x: - g(x)

    n = 125 # population size

    class IntegerIndividual(GA.Individual):

        BIT_SIZE = 10 # <= 16
        DOUBLE_CUTOFF = True

        def __init__(self, value: np.uint16):
            super().__init__(value)

        def crossover(self, other) -> tuple:
            cr1 : np.uint16 = self.value
            cr2 : np.uint16 = other.value

            pc1 = int(np.floor(np.random.uniform(0.05, 0.51)))
            cr1, cr2 = self.__crossover__(cr1, cr2, pc1)

            if self.DOUBLE_CUTOFF:
                pc2 = int(np.floor(np.random.uniform(0.50, 0.95)))
                cr1, cr2 = self.__crossover__(cr1, cr2, pc2)

            return IntegerIndividual(cr1), IntegerIndividual(cr2)
        
        @staticmethod
        def __crossover__(cr1 : np.uint16, cr2 : np.uint16, pc : int) -> tuple[np.uint16, np.uint16]:
            aux_b = pc
            aux_a = 65535 - pc

            cr1_a = cr1 & aux_a
            cr1_b = cr1 & aux_b

            cr2_a = cr2 & aux_a
            cr2_b = cr2 & aux_b

            new_cr1 = cr1_a | cr2_b
            new_cr2 = cr2_a | cr1_b

            return new_cr1, new_cr2    
        
        def mutate(self) -> None:
            r = np.random.randint(0, self.BIT_SIZE)  

            mask : np.uint16 = 1 << r           

            new_cr : np.uint16 = self.value ^ mask # XOR   

            self.value = new_cr

        def fitness(self) -> float:
            return f(self.value)
            
        def __str__(self):
            return f'ShortInteger: value: {self.value}'
        
    pop = np.random.randint(0, 2 ** IntegerIndividual.BIT_SIZE, size=n, dtype=np.uint16)

    pop = [IntegerIndividual(x) for x in pop]

    ga_models = [
        GA(RouletteWheel(15)),
        GA(RouletteWheel(50)),
        GA(Tournament(15, 10)),
        GA(Tournament(50, 10)),
    ]

    colors = [
        'blue',
        'green',
        'orange',
        'red',
        'purple',
        'brown'
    ]

    generations = 1500
    mut_prob = 0.05

    for i, ga in enumerate(ga_models):
        x = ga.run(pop, generations, mut_prob)

        print(f" {x=} {g(x)=}")
        plt.scatter(x, g(x), color=colors[i])

    max_x = 2 ** IntegerIndividual.BIT_SIZE

    t = np.linspace(0, max_x, max_x * 4)
    y = g(t)

    plt.plot(t, y, color='black')
    description = f"Population Size: {n} | Mutation probability: {mut_prob * 100}% | Generations: {generations}"
    plt.figtext(0.5, -0.05, description, ha='center', fontsize=10)
    plt.legend([f"{ga}" for ga in ga_models], loc='center left', bbox_to_anchor=(1, 0.5)) 
    plt.title("GA Tests")
    cutofftype = 'singlecut'
    if IntegerIndividual.DOUBLE_CUTOFF:
        cutofftype = 'doublecut'

    plt.savefig(f"{tests_results_dir}/find_min_{IntegerIndividual.BIT_SIZE}bits_{cutofftype}.png", bbox_inches='tight')
    