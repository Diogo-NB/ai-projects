import numpy as np

def my_order_crossover(parent1: np.ndarray, parent2: np.ndarray) -> tuple:
    n = parent1.size
    pc1, pc2 = 2, 5 # sorted(np.random.choice(n, 2, replace=False))

    child1 = parent1[pc1:pc2]
    child2 = parent2[pc1:pc2]

    parent1_aux = [city for city in parent1 if city not in child2]
    parent2_aux = [city for city in parent2 if city not in child1]
    
    child1 = np.concatenate((parent2_aux[:pc1], child1, parent2_aux[pc1:]))
    child2 = np.concatenate((parent1_aux[:pc1], child2, parent1_aux[pc1:]))

    return child1, child2

def order_crossover(parent1: np.ndarray, parent2: np.ndarray) -> tuple:
    n = len(parent1)
    p1, p2 = 2, 5 # sorted(np.random.choice(n, 2, replace=False))

    child1 = np.full(n, None, dtype=object)
    child2 = np.full(n, None, dtype=object)

    # Copy the segment from parent1 to child1
    child1[p1:p2] = parent1[p1:p2]
    child2[p1:p2] = parent2[p1:p2]

    # Fill the rest from parent2 to child1, maintaining the order
    current_pos = p2 % n
    for city in parent2:
        if city not in child1:
            child1[current_pos] = city
            current_pos = (current_pos + 1) % n

    # Do the same for child2
    current_pos = p2 % n
    for city in parent1:
        if city not in child2:
            child2[current_pos] = city
            current_pos = (current_pos + 1) % n

    return child1, child2

def pmx_crossover(parent1: np.ndarray, parent2: np.ndarray) -> tuple:
    n = len(parent1)
    p1, p2 = sorted(np.random.choice(n, 2, replace=False))

    child1 = parent1.copy()
    child2 = parent2.copy()

    # Swap the segment
    child1[p1:p2], child2[p1:p2] = parent2[p1:p2], parent1[p1:p2]

    # Handle the mapping
    def fix_mapping(child, parent_seg, other_seg, start, end):
        for i in range(start, end):
            if child[i] not in parent_seg:
                city = child[i]
                while city in other_seg:
                    idx = np.where(parent_seg == city)[0][0]
                    city = other_seg[idx]
                child[i] = city

    fix_mapping(child1, parent1[p1:p2], parent2[p1:p2], 0, p1)
    fix_mapping(child1, parent1[p1:p2], parent2[p1:p2], p2, n)

    fix_mapping(child2, parent2[p1:p2], parent1[p1:p2], 0, p1)
    fix_mapping(child2, parent2[p1:p2], parent1[p1:p2], p2, n)

    return child1, child2

test1 = np.array(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'])
test2 = np.array(['C', 'D', 'E', 'H', 'A', 'B', 'G', 'F'])

r11, r12 = my_order_crossover(test1, test2)

r21, r22 = order_crossover(test1, test2)

print(r11, r21)
print(r12, r22)