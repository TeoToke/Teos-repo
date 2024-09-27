//>using dep se.lth.cs::introprog::1.4.0
package blockmole

import java.awt.Color as JColor

object Color:
  val black  = new JColor(0,0,0)
  val mole   = new JColor(51,51,0)
  val soil   = new JColor(153,102,51)
  val tunnel = new JColor(204,153,102)
  val grass  = new JColor(25,130,35)
  val sky    = new JColor(83,193,237)



object BlockWindow:
  import introprog.PixelWindow
  val windowSize = (30,50)
  val blockSize  = 10

  type Pos = (Int, Int)

  def block(pos: Pos)(color: JColor = JColor.gray): Unit =
    val x = pos._1 * blockSize
    val y = pos._2 * blockSize
    window.fill(x, y, blockSize, blockSize, color)

  def rectangle(leftTop: Pos)(size: (Int, Int))(color: JColor): Unit =
    for y <- leftTop._2 to leftTop._2 + size._2 do
      for x <- leftTop._1 to leftTop._1 + size._1  do
        block(x, y)(color)


  val maxWaitMillis = 10

  def waitForKey(): String =
    window.awaitEvent(maxWaitMillis)
    while window.lastEventType != PixelWindow.Event.KeyPressed do
      window.awaitEvent(maxWaitMillis)
    println(s"KeyPressed: ${window.lastKey}")
    window.lastKey

  val window = new PixelWindow(windowSize._1 * blockSize, windowSize._2 * blockSize, "Blocky Molez")

  object tokeworld:
    def row(rowy: Int = 0, rowh: Int = 10)(rowcolor: JColor = JColor.gray): Unit =
      for i <- 0 to rowh do
        for g <- 0 to 30 do block(g,i+rowy)(rowcolor)

    def sky: Unit =
    row(0, 20)(Color.sky)

    def grass: Unit =
    row(20, 2)(Color.grass)
  
    def earth: Unit =
    row(22, 28)(Color.soil)

    def drawtokeworld: Unit =
      sky
      grass
      earth



object Mole:
  def dig(): Unit = 
    println("Time to dig baby!")
    var x = BlockWindow.windowSize._1 / 2
    var y = BlockWindow.windowSize._2 / 2
    var quit = false
    while !quit do
      BlockWindow.block(x, y)(Color.mole)
      val key = BlockWindow.waitForKey()
      if      key == "w" then {BlockWindow.block(x,y)(Color.tunnel); y = y -1}
      else if key == "a" then {BlockWindow.block(x,y)(Color.tunnel); x = x -1}
      else if key == "s" then {BlockWindow.block(x,y)(Color.tunnel); y = y +1}
      else if key == "d" then {BlockWindow.block(x,y)(Color.tunnel); x = x +1}
      else if key == "q" then run

object Main:
  def drawWorld(): Unit = 
    println("Drawing World...")
    Thread.sleep(500)
    BlockWindow.window
    BlockWindow.rectangle(0,0)(30,4)(Color.grass)
    BlockWindow.rectangle(0,4)(30,46)(Color.soil)

@main def run =
  Main.drawWorld()
  Mole.dig()