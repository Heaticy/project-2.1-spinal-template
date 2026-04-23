package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class StageDecode() extends Component {
  val io = new Bundle {
    // Instruction
    val instr = in(Bits(dataWidth bits))
    // Data of rs1 (output)
    val rs1Data = out(Bits(dataWidth bits))
    // Data of rs2 (output)
    val rs2Data = out(Bits(dataWidth bits))
    // Data of rd (input)
    val rdData = in(Bits(dataWidth bits))
    // Extended imm (output)
    val immExt = out(Bits(dataWidth bits))
    // Writeback select
    val resultSrc = out(ResultSrc())
    // Memory write enable
    val memWrite = out(Bool())
    // Memory operation
    val memOp = out(MemOp())
    // BranchUnit operation
    val branchOp = out(BranchOp())
    // Alu operation
    val aluOp = out(AluOp())
    // Int srca source select
    val intSrcA = out(IntSrcA())
    // Int srcb source select
    val intSrcB = out(IntSrcB())
    // Pc adder srcb source select
    val pcAddSrcA = out(PcAddSrcA())
  }

  // ControlUnit
  val cu = ControlUnit().setName("cu_u")
  // RegisterFile
  val rf = RegisterFile().setName("rf_u")
  // ImmExtend
  val ext = ImmExtend().setName("ext_u")

  // TODO
}

object StageDecodeSim extends App {
  Config.sim.compile(StageDecode()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object StageDecodeVerilog extends App {
  Config.spinal.generateVerilog(StageDecode())
}

object StageDecodeVhdl extends App {
  Config.spinal.generateVhdl(StageDecode())
}
