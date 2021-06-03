
export type ElkGraphUtil = {
    createGraph: () => ElkNode;
    createNode: (parent: ElkNode) => ElkNode
    createSimpleEdge: (node1: ElkNode, node2: ElkNode) => void;
}
export type ElkEdge = {
    getSources: () => ElkNode[]
    getTargets: () => ElkNode[]
}
export type ElkNode = {
    getChildren: () => Iterable<{ getIdentifier: () => string }>;
    getContainedEdges: () => Iterable<ElkEdge>;
    getIdentifier: () => string;
    setIdentifier: (id: string) => void;
}