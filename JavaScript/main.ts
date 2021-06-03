/* to be compiled using tsc:
 install using:
 npm install -g typescript
 then compile in watch mode using: 
 tsc -w main.ts --downlevelIteration
 or using npx
 npx tsc -w main.ts --downlevelIteration
*/

declare var Java: pieceOfCrap;
type pieceOfCrap = any;

import type { ElkGraphUtil, ElkEdge, ElkNode } from "./types"

//proper type checking ElkNode -> ElkNode
function layout(graph: ElkNode): ElkNode {

    const nodes = [...graph.getChildren()].map((n) => n.getIdentifier());
    const edges = [...graph.getContainedEdges()].map(e => ({
        from: e.getSources()[0].getIdentifier(),
        to: e.getTargets()[0].getIdentifier()
    }));

    console.log("nodes from ts: " + nodes);
    console.log("edges from ts: " + JSON.stringify(edges) + "\n");



    const ElkGraphUtil = Java.type('org.eclipse.elk.graph.util.ElkGraphUtil') as ElkGraphUtil;

    const testGraph = ElkGraphUtil.createGraph();
    testGraph.setIdentifier("root");

    const n3 = ElkGraphUtil.createNode(testGraph);
    n3.setIdentifier("n3");
    const n4 = ElkGraphUtil.createNode(testGraph);
    n4.setIdentifier("n4");

    ElkGraphUtil.createSimpleEdge(n3, n4);
    return testGraph;
};
