package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class StageMemory() extends Component {
  val io = new Bundle {
    // Alu result (input as addr)
    val aluResult = in(Bits(dataWidth bits))
    // Data of rs2 (input as write data)
    val rs2Data = in(Bits(dataWidth bits))
    // Mem result (output as read data)
    val memResult = out(Bits(dataWidth bits))
    // Mem write enable
    val memWrite = in(Bool())
    // Mem operation
    val memOp = in(MemOp())
    // Dmem interface
    val dmem = master(PortDmem())
  }

  // TODO
}

object StageMemorySim extends App {
  Config.sim.compile(StageMemory()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object StageMemoryVerilog extends App {
  Config.spinal.generateVerilog(StageMemory())
}

object StageMemoryVhdl extends App {
  Config.spinal.generateVhdl(StageMemory())
}
