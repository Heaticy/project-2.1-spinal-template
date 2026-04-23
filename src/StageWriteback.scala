package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class StageWriteback() extends Component {
  val io = new Bundle {
    // Alu result
    val aluResult = in(Bits(dataWidth bits))
    // Mem result
    val memResult = in(Bits(dataWidth bits))
    // Pc + 4
    val pcPlus4 = in(Bits(addrWidth bits))
    // Writeback select
    val resultSrc = in(ResultSrc())
    // Data of rd (writeback data)
    val rdData = out(Bits(dataWidth bits))
  }

  // TODO
}

object StageWritebackSim extends App {
  Config.sim.compile(StageWriteback()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object StageWritebackVerilog extends App {
  Config.spinal.generateVerilog(StageWriteback())
}

object StageWritebackVhdl extends App {
  Config.spinal.generateVhdl(StageWriteback())
}
