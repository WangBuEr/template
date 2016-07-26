<%@include file="../common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>

</style>
<div id="pjax-container" class="pjax-container container">
	<div class="col-sm-12" >
		<h2>
			1.容量瓶颈
		</h2>
		<ol style="margin-left: 4em;">
			<li>Linux下一个目录中的文件数量以不超过300为宜，这样换算，一个库中的表，不应高于100个左右；</li>
			<li>Mysql最大连接16384。</li>
			<li>单表容量受限于操作系统。</li>
		</ol>
	</div>
	
	<div class="col-sm-12">
		<h2>
			2.性能瓶颈
		</h2>
		<ol style="margin-left: 4em;">
			<li>单表容量大于1000W检索数据效率极低；</li>
		</ol>
	</div>
	
	<div class="col-sm-12">
		<h2>
			3.数据切分
		</h2>
		<p class="indent">
			数据切分根据其切分规则的类型，可以分为两种切分模式。
			一种是按照不同的表来切分到不同的数据库之上，这种切分可以称之为数据的<span class="lead red">垂直（纵向）切分</span>;
			另外一种规则是根据表中数据的逻辑关系，将同一个表中的数据按照某种条件拆分到多个表中或多台数据库之上，
			这种切分称之为数据的<span class="lead red">水平（横向）切分</span>。
		</p>
		<div>
			<h3>3.1垂直切分</h3>
			<p class="indent">
				数据的垂直切分，也可以称为纵向切分。将数据库想象成由很多个一大块一大块的“数据块”（表）组成，垂直地将这些“数据块”切开，
				然后把他们分散到多台数据库主机上面。这样的切分方法就是垂直（纵向）的数据块切分。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分库</span>）：
			</p>
			<p>
				
			</p>
			<p class="indent">
				垂直切分的优点：
			</p>
			<ul style="margin-left: 3em;">
				<li>数据库的拆分简单明了，才分规则明确；</li>
				<li>应用程序模块清晰明确，整合容易；</li>
				<li>数据维护方便易行，容易定位</li>
			</ul>
			<p class="indent">
				垂直切分的缺点：
			</p>
			<ul style="margin-left: 3em;">
				<li>部分表关联无法再数据库级别完成，要在程序中完成；</li>
				<li>对于访问集齐频繁且数据量超大的表仍然存在性能瓶颈，不一定能满足要求；</li>
				<li>事务处理相对复杂；</li>
				<li>切分大道一定程度之后，扩展性会受到限制；</li>
				<li>过度切分可能会带来系统获取复杂而难以维护。</li>
			</ul>
			<p class="lead red indent">
				垂直切分主要解决Mysql数据库的容量瓶颈问题,不能解决单表中数据量增长出现的容量及性能问题。
			</p>
		</div>
		<div>
			<h3>3.2水平切分</h3>
			<p class="indent">
				数据的水平切分，也可以称为横向切分。简单的水平切分主要是将某个访问及其频繁的表再按照某个字段的某种规则分散到多个表中，
				每个表包含一部分数据。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分表</span>）：
			</p>
			<p>
				
			</p>
			<p class="indent">
				水平切分的优点：
			</p>
			<ul style="margin-left: 3em;">
				<li>表关联基本能够在数据库端全部完成；</li>
				<li>不会存在某些超大型数据量和高负载的表遇到瓶颈的问题；</li>
				<li>应用程序端整体架构改动相对较少；</li>
				<li>事务处理相对简单；</li>
				<li>只要切分规则能定义好，基本上较难遇到扩展性限制。</li>
			</ul>
			<p class="indent">
				水平切分的缺点：
			</p>
			<ul style="margin-left: 3em;">
				<li>切分规则相对复杂，很难抽象出一个能够满足整个数据库的切分规则；</li>
				<li>后期数据的维护难度有所增加，人为手工定位数据更困难；</li>
				<li>应用系统各模块耦合度较高，可能会对后面数据的迁移拆分造成一定的困难。</li>
			</ul>
			<p class="lead red indent">
				水平切分主要解决Mysql数据库的性能瓶颈和单表容量限制问题。
			</p>
		</div>
		
		<div class="col-sm-12">
			<h3>3.3垂直与水平联合切分</h3>
			<p class="indent">
				对于那些负载并不是太大、业务逻辑也相对简单的系统可以通过上面两种切分方法之一来解决扩展性问题，
				对于业务逻辑复杂、系统负载大的系统，就需要将上述两种切分方法结合使用，不同的场景使用不同的切分方法。
			</p>
			<p class="indent">
				示意图（<span class="lead red">分库分表</span>）：
			</p>
			<p class="indent">
				示意图（<span class="lead red">多库多表</span>）：
			</p>
		</div>
	</div>
</div>