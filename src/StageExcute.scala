package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class StageExcute() extends Component {
  val io = new Bundle {
    // Data of rs1
    val rs1Data = in(Bits(dataWidth bits))
    // Data of rs2
    val rs2Data = in(Bits(dataWidth bits))
    // Extended imm
    val immExt = in(Bits(dataWidth bits))
    // Alu calculation result
    val aluResult = out(Bits(dataWidth bits))
    // Pc (to calculate pctarget = pc + imm)
    val pc = in(Bits(addrWidth bits))
    // Pc target
    val pcTarget = out(Bits(addrWidth bits))
    // BranchUnit operation
    val branchOp = in(BranchOp())
    // Alu operation
    val aluOp = in(AluOp())
    // Int srca source select
    val intSrcA = in(IntSrcA())
    // Int srcb source select
    val intSrcB = in(IntSrcB())
    // Pc adder srcb source select
    val pcAddSrcA = in(PcAddSrcA())
    // Pc next select
    val pcSrc = out(PcSrc())
  }

  // Alu
  val alu = Alu().setName("alu_u")
  // BranchUnit
  val bu = BranchUnit().setName("bu_u")

  // TODO
}

object StageExcuteSim extends App {
  Config.sim.compile(StageExcute()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object StageExcuteVerilog extends App {
  Config.spinal.generateVerilog(StageExcute())
}

object StageExcuteVhdl extends App {
  Config.spinal.generateVhdl(StageExcute())
}
