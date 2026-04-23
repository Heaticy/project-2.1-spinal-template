package Qin110

import spinal.core._
import spinal.core.sim._
import spinal.lib._
import Qin110.Constant._
import Qin110.{MainConfig => cfg}

case class Qin110Core() extends Component {
  val io = new Bundle {
    // Imem interface
    val imem = master(PortImem())
    // Dmem interface
    val dmem = master(PortDmem())
  }

  // Fetch
  val stageF = StageFetch()
  // Decode
  val stageD = StageDecode()
  // Excute
  val stageE = StageExcute()
  // Memory
  val stageM = StageMemory()
  // Writeback
  val stageW = StageWriteback()

  // Connect memory interface
  io.imem <> stageF.io.imem
  io.dmem <> stageM.io.dmem

  // Input of Stage F
  stageF.io.pcSrc := stageE.io.pcSrc
  stageF.io.pcTarget := stageE.io.pcTarget

  // Input of Stage D
  stageD.io.instr := stageF.io.instr
  stageD.io.rdData := stageW.io.rdData

  // Input of Stage E
  stageE.io.rs1Data := stageD.io.rs1Data
  stageE.io.rs2Data := stageD.io.rs2Data
  stageE.io.immExt := stageD.io.immExt
  stageE.io.pc := stageF.io.pc
  stageE.io.branchOp := stageD.io.branchOp
  stageE.io.aluOp := stageD.io.aluOp
  stageE.io.intSrcA := stageD.io.intSrcA
  stageE.io.intSrcB := stageD.io.intSrcB
  stageE.io.pcAddSrcA := stageD.io.pcAddSrcA

  // Input of Stage M
  stageM.io.aluResult := stageE.io.aluResult
  stageM.io.rs2Data := stageE.io.rs2Data
  stageM.io.memWrite := stageD.io.memWrite
  stageM.io.memOp := stageD.io.memOp

  // Input of Stage W
  stageW.io.aluResult := stageE.io.aluResult
  stageW.io.memResult := stageM.io.memResult
  stageW.io.pcPlus4 := stageF.io.pcPlus4
  stageW.io.resultSrc := stageD.io.resultSrc
}

object Qin110CoreSim extends App {
  Config.sim.compile(Qin110Core()).doSim { dut =>
    dut.clockDomain.forkStimulus(period = 10, resetCycles = 9)
    dut.clockDomain.waitRisingEdge()

  }
}

object Qin110CoreVerilog extends App {
  Config.spinal.generateVerilog(Qin110Core())
}

object Qin110CoreVhdl extends App {
  Config.spinal.generateVhdl(Qin110Core())
}
