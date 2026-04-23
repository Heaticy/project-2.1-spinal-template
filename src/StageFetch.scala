package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class StageFetch() extends Component {
  val io = new Bundle {
    // Pc next select
    val pcSrc = in(PcSrc())
    // Pc target (if jump)
    val pcTarget = in(Bits(addrWidth bits))
    // Pc + 4 (ouput for Mux in Stage W, jal required rd = pc + 4)
    val pcPlus4 = out(Bits(addrWidth bits))
    // Pc
    val pc = out(Bits(addrWidth bits))
    // Instruction
    val instr = out(Bits(dataWidth bits))
    // Imem interface
    val imem = master(PortImem())
  }

  // Pc register
  val pcReg = Reg(Bits(addrWidth bits)).simPublic() init (cfg.rom.base)

  // TODO
}

object StageFetchSim extends App {
  Config.sim.compile(StageFetch()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object StageFetchVerilog extends App {
  Config.spinal.generateVerilog(StageFetch())
}

object StageFetchVhdl extends App {
  Config.spinal.generateVhdl(StageFetch())
}
