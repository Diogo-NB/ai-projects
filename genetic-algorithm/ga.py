import numpy as np
from selection_methods import SelectionMethod
from individual import Individual

class GA:

    def __init__(self, selection: SelectionMethod, elitism: bool = False):
        self.selection = selection
        self.elitism = elitism

    def __str__(self):
        return f"GA[selection: {self.selection}, elitism: {self.elitism}]"

    def run(self, initial_pop : list[Individual], generations: int, mut_prob: float):
        pop : list[Individual] = np.array(initial_pop, dtype=Individual)
        best_result = pop[0].value

        for _ in range(generations):
            fitness : np.ndarray = np.array([Individual.fitness() for Individual in pop])

            if self.elitism:
                pop[np.argmin(fitness)].value = best_result

            best_result = pop[np.argmax(fitness)].value

            selected : list[Individual] = self.selection.select(fitness, pop)

            next_pop : list[Individual] = []

            for i in range(0, len(pop), 2):
                parent1, parent2 = np.random.choice(selected, 2, replace=False)

                child1, child2 = parent1.crossover(parent2)

                if np.random.random() <= mut_prob:
                    child1.mutate()
                if np.random.random() <= mut_prob:
                    child2.mutate()

                next_pop.append(child1)
                next_pop.append(child2)

            pop = np.array(next_pop, dtype=Individual)

        return pop[np.argmax(fitness)].value