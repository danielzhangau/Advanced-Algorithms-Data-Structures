import networkx as nx
from collections import namedtuple
import os

G = nx.DiGraph(name="G")
GT = nx.DiGraph(name="GT")

sni = "984503483052"

COLOR_LOOKUP_MAP = {
    "WHITE": "white",
    "PURPLE": "violet",
    "GRAY": "slategrey"
}


class Node(object):
    def __init__(self, uid):
        self.uid = uid
        self.color = "WHITE"
        self.pi = None

        self.d = None
        self.f = None

    def gen_label(self):
        t = ""

        if self.d:
            t += str(self.d) + "/"

        if self.f:
            t += str(self.f)

        return "{ " + str(self.uid) + " | " + str(t) + " }"

    def __hash__(self):
        return hash(self.uid)

    def __eq__(self, other):
        return self.uid == other.uid

    def __str__(self):
        return f"{self.uid} {self.color} {self.pi.uid if self.pi else 'none'} {self.d} {self.f}"


NODES = {k: Node(k) for k in "0123456789"}

critical_edges = []

for (k, v) in NODES.items():
    G.add_node(v)
    GT.add_node(v)

for (u, v) in zip(sni[:-1], sni[1:]):
    G.add_edge(NODES[u], NODES[v])
    GT.add_edge(NODES[v], NODES[u])


def dump_graph(G, t, without_backedges=False):
    tag = G.graph["name"]

    with open(f"graphs/{tag}-{t}.txt", "w") as f:

        f.write("digraph G {\n")

        for u in G.nodes:
            f.write("{} [fillcolor=\"{}\", style=\"filled\", shape=\"Mrecord\", label=\"{}\"]\n".format(
                u.uid,
                COLOR_LOOKUP_MAP[u.color],
                u.gen_label()
            ))

        for (u, v) in G.edges:
            if (u.uid, v.uid) in critical_edges:
                f.write("{h} -> {t} [penwidth=5.0]\n".format(
                    h=u.uid,
                    t=v.uid
                ))
            elif not without_backedges:
                f.write("{h} -> {t}\n".format(
                    h=u.uid,
                    t=v.uid
                ))
        f.write("}\n")

    os.system(f"dot -Tpng graphs/{tag}-{t}.txt > images/{tag}-{t}.png")
    print("\includegraphics[width=\2.2cm]{" + f"{tag}-{t}" + "}")


dump_graph(G, "graph")
dump_graph(GT, "transpose")

time = 0


def dfs(G, key):
    for u in G.nodes:
        u.color = "WHITE"
        u.pi = None

    for u in sorted(G.nodes, key=key):
        if u.color == "WHITE":
            print(u)
            dfs_visit(G, u)


def dfs_visit(G, u):
    global time
    time = time + 1
    u.d = time
    u.color = "PURPLE"
    dump_graph(G, time)

    for v in sorted(G.successors(u), key=lambda x: x.uid):
        if v.color == "WHITE":
            v.pi = u
            critical_edges.append((u.uid, v.uid))
            dfs_visit(G, v)

    u.color = "GRAY"
    time = time + 1
    u.f = time
    dump_graph(G, time)


print("Doing G")
time = 0
dfs(G, key=lambda x: x.uid)

print("Doing GT")
time = 0
dfs(GT, key=lambda x: -x.f)

dump_graph(G, "trees", without_backedges=True)
dump_graph(GT, "trees", without_backedges=True)

# nx.draw_networkx(G)
