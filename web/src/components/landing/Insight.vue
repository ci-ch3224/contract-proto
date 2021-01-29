<template>
  <v-container class="px-12">
    <v-row>
      <v-col class="md-8 mx-auto text-center">
        <span class="badge badge-primary badge-pill mt-16 mb-4">Insight</span>
        <h3 class="home-title my-10">{{ lawTitle }}</h3>
        <p class="home-title-contents">{{ lawContents }}</p>
      </v-col>
    </v-row>
    <v-row id="insight-contents" justify="space-around">
      <v-col id="insight-contents-first" class="ml-12" md="3">
        <v-icon class="insight-icons my-6">mdi-folder-edit</v-icon>
        <h4 class="mb-6">{{ signTitle }}</h4>
          <p>{{ signContentsFirst }}</p>
          <p>{{ signContentsSecond }}</p>
        <a :href=signContentsHref target="_sub">{{ signContentsLink }}
          <v-icon small>mdi-chevron-right</v-icon>
        </a>
      </v-col>
      <v-col id="insight-contents-second" md="3">
        <v-icon class="insight-icons my-6">mdi-folder-text</v-icon>
        <h4 class="mb-6">{{ documentTitle }}</h4>
          <p>{{ documentContentsFirst }}<p>
           <p>{{ documentContentsSecond }}<br/>{{ documentContentsThird }}</p>
        <a :href=documentContentsHref target="_sub">{{ documentContentsLink }}
          <v-icon small>mdi-chevron-right</v-icon>
        </a>
      </v-col>
      <v-col id="insight-contents-third" class="mr-12" md="3">
        <v-icon class="insight-icons my-6">mdi-handshake</v-icon>
        <h4 class="mb-6">{{ contractTitle }}</h4>
          <p>{{ contractContentsFirst }}</p>
        <a :href=contractContentsHref target="_sub">{{ contractContentsLink }}
          <v-icon small>mdi-chevron-right</v-icon>
        </a>
      </v-col>
    </v-row>
  </v-container>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class Insight extends Vue {
  lawTitle = '법적효력'
  lawContents = '전자서명법 및 전자문서법에 의거, 법적 효력이 있습니다.'
  signTitle = '전자서명 효력'
  signContentsFirst = '① 전자서명은 전자적 형태라는 이유만으로 서명, 서명날인 또는 기명날인으로서의 효력이 부인되지 아니한다.'
  signContentsSecond = '② 법령의 규정 또는 당사자 간의 약정에 따라 서명, 서명날인 또는 기명날인의 방식으로 전자서명을 선택한 경우 그 전자서명은 서명, 서명날인 또는 기명날인으로서의 효력을 가진다.'
  signContentsLink = '제3조(전자서명의 효력)'
  signContentsHref = 'https://www.law.go.kr/법령/전자서명법/(20201210,17354,20200609)/제3조'
  documentTitle = '전자문서 효력'
  documentContentsFirst = '① 전자문서는 전자적 형태로 되어 있다는 이유만으로 법적 효력이 부인되지 아니한다.'
  documentContentsSecond = '1. 전자문서의 내용을 열람할 수 있을 것'
  documentContentsThird = '2. 전자문서가 작성ㆍ변환되거나 송신ㆍ수신 또는 저장된 때의 형태 또는 그와 같이 재현될 수 있는 형태로 보존되어 있을 것'
  documentContentsLink = '제4조(전자문서의 효력)'
  documentContentsHref = 'https://www.law.go.kr/법령/전자문서및전자거래기본법/(20201210,17353,20200609)/제4조'
  contractTitle = '낙성불요식 계약원칙'
  contractContentsFirst = '민법에서 지정한 14종의 계약 중 현상광고(민법 제 675조)를 제외한 모든 계약은 다른 형식이나 절차가 필요없이 당사자 간 의사표시가 일치한다면 계약으로써 효력을 가진다.'
  contractContentsLink = '제532조(의사실현에 의한 계약성립)'
  contractContentsHref = 'https://www.law.go.kr/법령/민법/(20210126,17905,20210126)/제532조'
}
</script>
<style lang="sass">
.badge
  display: inline-block
  padding: 0.35rem 0.375rem
  font-size: 66%
  font-weight: 600
  line-height: 1
  text-align: center
  white-space: nowrap
  vertical-align: baseline
  border-radius: 0.25rem
  transition: all 0.15s ease

.badge-primary
  color: #fff
  background-color: #5e72e4

.badge-pill
  padding-right: 0.875em
  padding-left: 0.875em
  border-radius: 10rem

.home-title
  font-family: 'Nanum Square'
  color: #32325d
  font-size: 2.1875rem
  letter-spacing: 0.1875rem
  font-weight: 600
  line-height: 1.5

.home-title-contents
  font-family: 'Nanum Gothic'
  letter-spacing: 0.08rem
  color: #5e5f7f
  &::after
    content: ""
    display: block
    height: 40px
    width: 30px
    border-bottom: 3px solid #5e72e4
    margin: 0 auto

#insight-contents
  p
    font-family: 'Nanum Gothic'
    color: #525f7f
    font-size: 0.8rem
  a
    font-family: 'Nanum Gothic'
    color: #5e72e4
    font-size: 0.8rem
    text-decoration: none
    &:hover
      color: #233dd2
      .v-icon
        color: #233dd2
    .v-icon
      color: #5e72e4

#insight-contents-first
  h4
    font-family: 'Nanum Square'
    color: #5e72e4
  .insight-icons
    color: #2643e9
    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1), 0 5px 15px rgba(0, 0, 0, 0.07)
    background-color: #8a98eb80
    border-radius:50%
    height: 64px
    width: 64px

#insight-contents-second
  h4
    font-family: 'Nanum Square'
    color: #2dce89
  .insight-icons
    color: #1aae6f
    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1), 0 5px 15px rgba(0, 0, 0, 0.07)
    background-color: #54daa180
    border-radius:50%
    height: 64px
    width: 64px

#insight-contents-third
  h4
    font-family: 'Nanum Square'
    color: #fb6340
  .insight-icons
    color: #ff3709
    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1), 0 5px 15px rgba(0, 0, 0, 0.07)
    background-color: #fc8c7280
    border-radius:50%
    height: 64px
    width: 64px
</style>
