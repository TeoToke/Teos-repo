package blockbattle

class Mole(
    val name: String,
    var pos: Pos,
    var dir: (Int, Int),
    val color: java.awt.Color,
    val keyControl: KeyControl
):
    var points = 0

    override def toString =
        s"Mole[name=$name, pos=$pos, dir=$dir, points=$points]"

    
    def setDir(key: String): Unit =
        if keyControl.has(key) then dir = keyControl.direction(key)
        else dir = dir

    def reverseDir(): Unit = 
        (dir._2, dir._1)

    def move(): Unit = 
        pos.moved(dir)

    def nextPos: Pos =
        Pos(pos._1 + dir._1, pos._2 + dir._2)