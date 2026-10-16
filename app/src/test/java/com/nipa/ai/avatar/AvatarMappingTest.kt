package com.nipa.ai.avatar

import com.nipa.ai.modes.NipaMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Plain JVM unit tests (no Android runtime needed). Written in Phase 2; NOT yet executed. */
class AvatarMappingTest {

    @Test
    fun everyAvatarStateMapsToAnExpression() {
        AvatarState.entries.forEach { state ->
            AvatarExpression.forState(state) // must not throw
        }
    }

    @Test
    fun speakingAlternatesBetweenTalkingAndSmile() {
        assertEquals(AvatarExpression.TALKING, AvatarExpression.forState(AvatarState.SPEAKING, false))
        assertEquals(AvatarExpression.SMILE, AvatarExpression.forState(AvatarState.SPEAKING, true))
    }

    @Test
    fun errorStateUsesSadExpression() {
        assertEquals(AvatarExpression.SAD, AvatarExpression.forState(AvatarState.ERROR))
    }

    @Test
    fun reducedMotionRemovesBreathingSwayDriftAndTilt() {
        AvatarState.entries.forEach { state ->
            val p = MotionProfile.forState(state, reducedMotion = true)
            assertEquals(0f, p.breathAmplitude, 0f)
            assertEquals(0f, p.swayDegrees, 0f)
            assertEquals(0f, p.driftDp, 0f)
            assertEquals(0f, p.restTiltDegrees, 0f)
        }
    }

    @Test
    fun normalMotionKeepsIdleBreathing() {
        assertTrue(MotionProfile.forState(AvatarState.IDLE, false).breathAmplitude > 0f)
    }

    @Test
    fun curiousStateTiltsHead() {
        assertTrue(MotionProfile.forState(AvatarState.CURIOUS, false).restTiltDegrees > 0f)
    }

    @Test
    fun controllerStartsIdleAndTransitions() {
        val controller = AvatarController()
        assertEquals(AvatarState.IDLE, controller.state.value)
        controller.transitionTo(AvatarState.LISTENING)
        assertEquals(AvatarState.LISTENING, controller.state.value)
        controller.reset()
        assertEquals(AvatarState.IDLE, controller.state.value)
    }

    @Test
    fun modesHaveDistinctReactionStatesAndAccents() {
        val reactions = NipaMode.entries.map { it.reactionState }.toSet()
        val accents = NipaMode.entries.map { it.accentColor }.toSet()
        assertEquals(NipaMode.entries.size, reactions.size)
        assertEquals(NipaMode.entries.size, accents.size)
    }
}
